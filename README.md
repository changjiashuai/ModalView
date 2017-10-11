# ModalView
Android ModalView for Enhance Dialog.


# Demo

# Usage

```
    //一般使用，默认有动画（Animation实现）
    show(anim: Boolean = true)
    dismiss(anim: Boolean = true)
    
    //自定义显示、显示动画
    showWithAnimation(anim: Animation)
    dismissWithAnimation(anim: Animation)
    
    //实验
    showWithAnimator(animator: ObjectAnimator)
    dismissWithAnimator(animator: Animator)
    

    val modalView = ModalView(this).with {
        contentView = view
    }
    modalView.show()    //show
    modalView.dismiss() //dismiss
```

# Attribute

# Dependency

```
   dependencies {
     compile 'com.changjiashuai.modalview:modalview:1.0.3'
   }
```

# License