## 구현 내용 정리

### 0. Vector

- 과제에서 사용할 사용자 지정 타입
- x, y의 필드는 2차원 좌표를 의미
- parent와 neighbor는 A* 알고리즘 구현에 필요한 필드
- Vector는 도메인이지만 인스턴스로 생성되므로 많은 생성에는 제한이 필요 (메모리 낭비)
- 따라서 new를 이용한 Vector객체는 되도록 Map 클래스에서만 생성되도록 구현 필요

### 1. map/Map

- API를 통해 입력받은 데이터 (size, start, spots, hazards)를 이용하여 초기화
- 추후 데이터를 사용하기 쉽도록 Vector 타입의 2차원 배열을 생성 및 필드 대입

### 2. sensor/SensorController

- 각 센서 기능을 하나로 묶어서 사용하기 위한 객체
- 각 센서 클래스를 멤버로 선언 (Association)
- 해당 클래스는 오직 AddOn에서만 선언되고 사용됨

### 3. sensor/HazardSensor, PositioningSensor, SpotSensor

- 각 기능에 맞는 센서들
- 작업 후 통합 필요

### 4. sensor/SensorResult

- Sensor의 결과를 HAZARD, SPOT, SAFE로 표현할 Enum 클래스

### 5. movement/Direction

- 로봇의 방향을 표현할 Enum 클래스 / UP, DOWN, LEFT, RIGHT로 표현

### 6. movement/RobotMovementInterface

- 로봇의 이동을 제어하는 클래스
- 이동 후에는 항상 이동 완료된 위치를 반환해야 함
- 위에서 얻은 데이터와 논리적으로 이동해야하는 데이터를 AddOn에서 비교하여 정상 이동인지 판단
- 해당 클래스는 오직 AddOn에서만 사용되며 필드로 선언됨 (Association)

### 7. robot/Robot

- 로봇이 바라보는 방향의 정보를 가지고 있음
- 굳이 필요한지는 회의 필요 & Robot 클래스를 없앤다면 방향 데이터는 PositionSensor로 이동 필요

### 8. addon/AddOn

- 필드로 RobotMovementInterface와 SensorController를 가지며 해당 기능을 이용하여 로봇의 이동 제어
- 길을 찾는 pathfinding 메서드까지 구현 완료
    - 논리적으로 알맞은 경로를 List로 반환하며, 해당 데이터는 초기 로봇 이동 경로를 의미
    - **추후, 센서 결과 값을 응용하여 길찾는 로직으로 업데이트 필요
    - **이동의 결과를 이용하여 잘못된 이동에 대하여 보상하는 로직 필요
- **다음 이동할 논리적 좌표와 실제 움직인 좌표를 비교하여 True/False를 반환하는 메서드 필요

### 9. addon/Astar

- AddOn에서 사용하는 길찾기 로직의 기능 제공
- 오직 AddOn에서만 사용되며, 메서드 안에서 선언되어 사용됨 (depandency)

### 10. controller/Controller

- 주요 기능인 AddOn과 Map 객체를 묶어서 API와 통신하도록 하기위한 객체
- API로부터 데이터를 받으면 Map 설정 필요 (Map 필드 필요성)
- API에서 AddOn의 기능을 호출하여 요청에 알맞은 데이터 반환 필요 (AddOn 필드 필요성)

### 11. api/RestApi

- PostMapping("api/mapInit")
    - 초기 데이터 입력을 위함
    - Vector 객체에는 parent와 neighbor의 필드도 존재하므로 Dto로 변환하여 JSON 타입으로 반환
- GetMapping("api/pathfinding")
    - 로봇의 현재 위치와 방향을 고려하여 길찾기 로직을 실행하여 결과를 JSON으로 반환
    - Dto를 이용하여 path에 묶어서 반환