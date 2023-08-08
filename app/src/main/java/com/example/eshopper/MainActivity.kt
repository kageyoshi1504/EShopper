package com.example.eshopper

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.bumptech.glide.Glide
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.getWidth
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.ActivityMainBinding
import com.example.eshopper.users.main.model.Event.Badge
import com.example.eshopper.users.main.model.UserModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : LocalizationActivity(){
    private  val binding : ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navController: NavController
    private lateinit var mAuth : FirebaseAuth
    private lateinit var dialog : LoadingDialog
    private lateinit var dbRef : DatabaseReference
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setLanguage("vi")

        mAuth = FirebaseAuth.getInstance()
        initNavController()
        initView()
        initActions()
        setupDrawerLayout()

        EventBus.getDefault().register(this)




    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        FirebaseAuth.getInstance().removeAuthStateListener (authStateListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun initNavController()
    {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener {_,destination,_ ->
            val title = when(destination.id)
            {
                R.id.userFragment->"Home"
                R.id.categoryFragment ->getString(R.string.category)
                else -> null
            }


            setAppbarAndBottomNavigation(isVisible = title != null)
        }


    }

    private  fun initActions()
    {

        // Check Login


        binding.apply {
            toolBar.setNavigationOnClickListener{
                drawerLayout.openDrawer(GravityCompat.START)
            }
            toolBar.setOnMenuItemClickListener{
                item ->
                when(item.itemId)
                {
                    R.id.cart -> {
                        navController.navigate(R.id.userHomeToCart)
                    }
                }
                true
            }

        }
        val headerContainer : View = binding.navigationView.getHeaderView(0)
        val settings = headerContainer.findViewById<ImageView>(R.id.settings)
        settings.setOnClickListener{
           binding.drawerLayout.closeDrawer(GravityCompat.START)
            navController.navigateUp()

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()

            navController.navigate(R.id.settingsFragment, null, navOptions)
        }

        val home = headerContainer.findViewById<CardView>(R.id.cardViewHome)
        home.setOnClickListener{
            binding.drawerLayout.closeDrawer(GravityCompat.START)


        }

        val categories = headerContainer.findViewById<CardView>(R.id.cardViewCategory)
        categories.setOnClickListener {

        }

        val order = headerContainer.findViewById<CardView>(R.id.cardViewCart)
        order.setOnClickListener{
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            navController.navigate(R.id.userHomeToCart)
        }

        val history = headerContainer.findViewById<CardView>(R.id.cardViewHistory)
        history.setOnClickListener{
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            navController.navigate(R.id.userToHistory)
        }


    }

    private fun setAppbarAndBottomNavigation(isVisible: Boolean)
    {
        binding.apply {
            appBar.isVisible = isVisible
        }
    }

    private fun initView()
    {

        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        authStateListener = FirebaseAuth.AuthStateListener {
            firebaseAuth ->
            val user = firebaseAuth.currentUser

            if (user != null)
            {
                setUser(user)
            }
            else{
                navController.navigate(R.id.loginFragment)
            }
        }






    }

    override fun onStart() {
        super.onStart()
        initView()
        FirebaseAuth.getInstance()
            .addAuthStateListener  (authStateListener)    }




    private fun setupDrawerLayout()
    {

        binding.apply {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            navigationView.layoutParams.width = (applicationContext.getWidth() * 0.8).toInt()
            navigationView.requestLayout()
        }
    }



    private fun checkUserRole(userId: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users").child(userId)

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val isAdmin = snapshot.child("isAdmin").getValue(Boolean::class.java)

                    if (isAdmin == true) {
                        // Người dùng có quyền admin, chuyển hướng đến giao diện admin
                        navController.navigate(R.id.adminHomeFragment)
                    } else {
                        // Người dùng không có quyền admin, chuyển hướng đến giao diện user
                        navController.navigate(R.id.userFragment)
                    }
                } else {
                    // Xử lý khi không tìm thấy thông tin người dùng trong Database
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi khi đọc dữ liệu từ Database
            }
        })
    }




    private fun setUser(user : FirebaseUser)
    {
        val headerContainer : View = binding.navigationView.getHeaderView(0)
        val avatar = headerContainer.findViewById(R.id.imageUser) as ImageView
        val name = headerContainer.findViewById(R.id.nameTv) as TextView
        val email = headerContainer.findViewById(R.id.emailTv) as TextView

       dbRef.child(user.uid).addValueEventListener(object :ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
               if (snapshot.exists())
               {
                   val users = snapshot.getValue(UserModel::class.java)
                   if (users!=null)
                   {
                       name.text =users.name
                       email.text = users.email
                       Glide.with(baseContext).load(users.avatar).into(avatar)
                   }
               }
           }

           override fun onCancelled(error: DatabaseError) {
               TODO("Not yet implemented")
           }

       })
    }

    @Subscribe(sticky = true , threadMode = ThreadMode.MAIN)
    @androidx.annotation.OptIn(com.google.android.material.badge.ExperimentalBadgeUtils::class)
     fun  badgeMenu(event : Badge)
    {
        val badge = BadgeDrawable.create(this)
        BadgeUtils.attachBadgeDrawable(badge,binding.toolBar,R.id.cart)
        if (event.size >= 0)
        {
            badge.number = event.size

        }
        else {
            badge.number = 0

        }
    }


}


