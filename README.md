# SWE_Project 팀 구성 및 역할
- sung-yeop : BackEnd AddOn 기능 구현 & 통합 테스트 & 프로젝트 총괄
- dohunkim123 : FrontEnd 기능 구현
- water-potato : BackEnd Sim 기능 구현 & 음성 인식

## ⭐ Target of Project
- 사용자는 초기 지도 데이터를 입력한다.
- 가상의 로봇이 지도에 미리 설정된 위험 지점을 제외하고 탐색 지점을 모두 지나가는 경로를 찾는다.
- 이 때, 로봇은 위험 지점에 대한 정보를 모르고 있으며 **센서**기능을 활용하여 위험 지점인지 아닌지를 판별한다.
- 경로를 지나면서 센서 기능을 활용하여 중요 지점을 확인한다.
- 음성 인식 기능을 통하여 Hidden 지점을 추가할 수 있어야 한다.
- 위의 과정을 모두 진행하며 발생되는 이벤트를 UI에 표시하며, 경로를 따라 로봇이 이동하는 모습을 사용자에게 보인다.


## 🔍 Functional Requirements
### FrontEnd
- 지도 데이터를 사용자로부터 입력받는 기능을 구현한다.
- 사용자가 요구하는 기능 (initial, proceed, update)을 판별하여 BackEnd로 기능을 요청하도록 구현한다.

### Sim
- Sim 시스템은 총 2가지의 기능을 담당하는 서브 시스템들로 구성되어있다.
  - 로봇의 움직임을 담당하는 시스템을 구현한다.
      - 로봇은 오직 지정된 방향으로 한 칸만 이동한다.
      - 로봇의 회전은 오직 시계 방향으로 90도 회전만 제공한다.
      - 로봇은 5% 확률로 지정된 방향으로 2칸 이동하는 오작동이 발생할 수 있다.
  - 센서의 기능을 구현한다.
    - 센서는 총 3가지로 구성된다.
      - Hazard Sensor : 로봇의 전방이 Hazard인지 아닌지를 판별하는 Sensor이다.
      - ColorBlob Sensor: 로봇의 현재 위치를 기준으로 상하좌우에 중요 지점이 존재하는지 판별하는 Sensor이다.
      - Positioning Sensor: 로봇의 오작동의 발생 여부를 탐지하기 위한 Sensor이다.
   
### AddOn
- AddOn은 사용자의 요구에 맞춰 기능을 제공하기 위해 Sim에 dependancy를 가지고 있다.
- AddOn은 Sim의 기능을 모두 제공하며 AddOn에서만 제공하는 부가적인 기능은 아래와 같다.
  - 탐색 지점을 모두 지나는 경로를 반환하는 기능을 구현한다.

### Vocal Recognition
- Hidden spot을 추가하는 방법으로 음성 인식 기능을 제공한다.

***

## 📑 동작 매커니즘 & 주요 소스코드 설명
- **FrontEnd와 BackEnd는 REST API를 통해 Json 형태로 데이터를 주고 받는다.**
- BackEnd 소스코드는 src/main/java/com/SWE_Project/BackEnd에 위치한다.
- FrontEnd 소스코드는 src/main/resources/static에 위치한다.
- **src/main/resources/static/javascript/FrontController.js** : UI를 통해 사용자로부터 데이터를 받으면 FrontController에서 요청값에 따라 BackEnd의 BackController로 기능을 호출한다.
  - initialize : 사용자가 초기 데이터를 입력하면 해당 함수가 실행되며, 입력한 데이터를 map, start, spot, hazard, colorBlob로 정제하여 BackController에게 전달한다.
  - proceed : 사용자가 이동 버튼을 누르면 로봇이 경로를 따라 한 칸 이동한다.
  - update : 사용자가 음성인식으로 데이터를 입력하면 이를 정제하여 BackController로 기능을 호출한다.
- **src/main/java/com/SWE_Project/BackEnd/AddOn/BackController.java** : FrontController를 통해 기능이 호출되면 AddOn, Sim의 기능을 활용하여 요청된 기능을 제공한다.
  - init : 사용자가 입력한 초기 데이터를 BackEnd의 Map 데이터에 저장한다.
  - move : 사용자가 이동을 요청하면 FrontEnd로부터 받은 다음 지점으로 이동하도록 로봇의 방향을 세팅하고 움직이도록 명령한다. 다음 타겟 지점에 대한 Sensing을 진행하여 문제가 발생한다면 경로 재설정을 진행한다. 또한, 움직임 이후
    positioning senser를 통해 오작동을 감지하였다면 그에따른 경로를 재설정한다. 만약 경로가 재설정 되었거나 탐지된 새로운 지점이 존재한다면 이에 대한 정보를 반환 값에 추가하여 FrontEnd로 전달한다.
  - initHidden : 사용자가 입력한 음성 인식 데이터에 맞춰서 Hidden 좌표를 저장한다.
