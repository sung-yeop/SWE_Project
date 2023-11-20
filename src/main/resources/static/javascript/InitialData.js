var predefinedSpot, hazard0, mobileRobot, colorBlob;

function setMap(mapText, startText, spotText, hazardText) {
    // 각각의 텍스트를 정수 배열로 변환
    predefinedSpot = mapText.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    mobileRobot = startText.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    colorBlob = spotText.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    hazard0 = hazardText.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);

    var arr1 = [predefinedSpot, mobileRobot, colorBlob, hazard0];
    return arr1;
}
