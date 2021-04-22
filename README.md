# MJU-TeamProject2
TeamProject 2

## Android Convention Rule(리소스 네이밍)
* 이미지 파일: 특정 액티비티에 속한 이미지 -> type_activityname_filename.*
    * e.g. GetStartedActivity layout에 쓰인 이미지 파일 -> ic(img)_get_started_filename.png
    * e.g. 'copyright'로 파일 이름이 시작하면 xml(액티비티 아님)에 쓰인 이미지 파일 -> copyright_image.png

## project 관리
* 패키지 세분화: myclass, mbti, account, recyclerview_item, etc.
* 리사이클러뷰 관련 클래스(adapter, viewholder, decoration .. etc 는 개별 패키지로 관리하지 않고, 편의성을 위해서 임시로 activity와 함께 관리)

## Open source used
- circle imageview: https://github.com/hdodenhof/CircleImageView
- custom EditText: https://github.com/florent37/MaterialTextField
- custom spinner: https://github.com/Chivorns/SmartMaterialSpinner
- custom loading button: https://github.com/roynx98/transition-button-android
- DxLoadingButton: https://github.com/StevenDXC/DxLoadingButton
- timetable
  1. https://github.com/tlaabs/TimetableView
  2. https://androidexample365.com/customizable-timetableview-for-android
- custom toggle buttom: https://github.com/Bryanx/themed-toggle-button-group
  - reference
    1. https://woovictory.github.io/2020/06/13/Android-FlexBoxLayout/
- custom button: https://github.com/JMaroz/RoundButton

<!-- ## Get Started Activity -->
<!-- ![image](https://user-images.githubusercontent.com/43941383/114131739-1ab4a300-993e-11eb-8497-7c7785d186e0.png)-->

