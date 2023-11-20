## 전체 로직

### 1. 프론트에서 초기 경로 요청 -> 백엔드는 초기 경로를 확정하여 프론트로 전달

- 맵 데이터를 입력하고 저장하는 url : /api/init/
- POST 요청
- Front에도 MapData를 가지고 있으니 아무것도 반환하지 않음
    - 만약 데이터가 필요하다면 반환 형식은 아래와 같음
    - {
      "path": [{"vector": "(2 3)"}, {"vector": "(2 4)"}...],  
      "hazardList": [{"vector": "(3 5)"}, {"vector": "(4 5)"}, {"vector": "(6 7)"}],  
      "colorBlobList": [{"vector": "(10 10)"}],  
      "spotList": [{"vector": "(3 4)"}, {"vector": "(7 7)"}, {"vector": "(8 10)"}],  
      "currentPosition": {"vector": "(2 3)"}
    - }

### 2. 프론트에서는 해당 초기 경로를 저장 / 이동 버튼을 누를 때마다 저장된 경로를 따라서 이동하도록 백엔드로 요청

- 로봇의 이동을 요청하는 url : /api/move/
- GET 요청
    - 오동작 X, Sensor 작동 후 ColorBlob, Hazard Spot을 탐지하지 못했을 경우 반환 형식 (로봇의 현재 위치만 반환)
    - {  
      "path": null,  
      "hazardList": null,  
      "colorBlobList": null,  
      "spotList": null,  
      "currentPosition": {"vector": "(2 4)"}  
      }
    - ***
    - 오동작 O or Sensor 작동 후 ColorBlob, Hazard Spot을 탐지했을 경우 반환 형식 (현재 위치 + 변경된 내용만 반환)
    - {  
      "path": [{"vector": "(2 4)"}, {"vector": "(3 4)"}, {"vector": "(3 3)"}...],  
      "hazardList": [{"vector": "(3 5)"}, {"vector": "(4 5)"}, {"vector": "(6 7)"}, {"vector": "(4 4)"}],    
      "colorBlobList": null,  
      "spotList": null,  
      "currentPosition": {"vector": "(3 4)"}  
      }
