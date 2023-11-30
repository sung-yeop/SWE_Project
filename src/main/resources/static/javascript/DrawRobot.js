//drawAfterMove*********************************************************************************
function drawAfterMove(mapdata, path) {
    var patharr = path.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    cols = mapdata[0];
    rows = mapdata[1];

    var mapgrid = document.getElementById("myGrid");
    var cellwidth = mapgrid.offsetWidth / cols;
    var cellheight = mapgrid.offsetHeight / rows;

    var robotpos = document.getElementById("robot");
    robotwidth = robotpos.offsetWidth / 2;
    robotheight = robotpos.offsetHeight / 2;

    robotpos.style.left = -robotwidth + cellwidth * patharr[0] + "px";
    robotpos.style.top = -robotheight + cellheight * (rows - patharr[1]) + "px";

}

//drawAfterMove*********************************************************************************
//rotate*********************************************************************************
function rotate(path, current) {
    var patharr = path.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    var currentarr = current.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    var left = patharr[0] - currentarr[0];
    var top = patharr[1] - currentarr[1];

    var image = document.getElementById('robot');

    if (top > 0) {
        var degree = -90;
        image.style.transform = 'rotate(' + degree + 'deg)';
    }
    if (top < 0) {
        var degree = 90;
        image.style.transform = 'rotate(' + degree + 'deg)';
    }
    if (left > 0) {
        var degree = 0;
        image.style.transform = 'rotate(' + degree + 'deg)';
    }
    if (left < 0) {
        var degree = -180;
        image.style.transform = 'rotate(' + degree + 'deg)';
    }

}