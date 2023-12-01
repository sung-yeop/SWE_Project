//drawGrid*********************************************************************************
function drawGrid(mapdata) {
    cols = mapdata[0];
    rows = mapdata[1];
    var box = document.getElementById('myGrid');
    var boxWidth = box.offsetWidth;
    var boxHeight = box.offsetHeight;

    var children = box.children;
    for (var i = 0; i < children.length; i++) {
        children[i].style.display = 'none'; // 각 요소를 숨김 처리
    }

    // 가로선 생성
    for (var i = 1; i < rows; i++) {
        var horizontalLine = document.createElement('div');
        horizontalLine.classList.add('line', 'horizontal-line');
        horizontalLine.style.top = (boxHeight / rows) * i + 'px';
        box.appendChild(horizontalLine);
    }

    // 세로선 생성
    for (var j = 1; j < cols; j++) {
        var verticalLine = document.createElement('div');
        verticalLine.classList.add('line', 'vertical-line');
        verticalLine.style.left = (boxWidth / cols) * j + 'px';
        box.appendChild(verticalLine);
    }
}

//drawGrid*********************************************************************************
//drawUnit*********************************************************************************
function drawUnit(mapdata) {
    cols = mapdata[0][0];
    rows = mapdata[0][1];
    start = mapdata[1];
    spot = mapdata[2];
    gray = mapdata[3].concat(mapdata[4]);

    var mapgrid = document.getElementById("myGrid");
    var cellwidth = mapgrid.offsetWidth / cols;
    var cellheight = mapgrid.offsetHeight / rows;
    //로봇
    var robotpos = document.getElementById("robot");
    robotpos.style.display = "block";
    robotwidth = robotpos.offsetWidth / 2;
    robotheight = robotpos.offsetHeight / 2;

    robotpos.style.left = -robotwidth + cellwidth * start[0] + "px";
    robotpos.style.top = -robotheight + cellheight * (rows - start[1]) + "px";

    //spot
    var spotpos = document.getElementById("predefinedSpot");
    spotpos.style.display = "block";
    spotwidth = spotpos.offsetWidth / 2;
    spotheight = spotpos.offsetHeight / 2;
    spotpos.style.left = -spotwidth + cellwidth * spot[0] + "px";
    spotpos.style.top = -spotheight + cellheight * (rows - spot[1]) + "px";

    var spotlen = spot.length / 2;
    if (spotlen > 1) {
        for (var i = 1; i < spotlen; i++) {
            var clonedspot = document.createElement('img');
            clonedspot.src = spotpos.src;
            clonedspot.classList.add('predefinedSpot');
            clonedspot.style.display = "block";
            clonedspot.style.left = -spotwidth + cellwidth * spot[i * 2] + "px";
            clonedspot.style.top = -spotheight + cellheight * (rows - spot[i * 2 + 1]) + "px";
            mapgrid.appendChild(clonedspot);
        }
    }
    //gray
    var graypos = document.getElementById("gray");
    graypos.style.display = "block";
    graywidth = graypos.offsetWidth / 2;
    grayheight = graypos.offsetHeight / 2;
    graypos.style.left = -graywidth + cellwidth * gray[0] + "px";
    graypos.style.top = -grayheight + cellheight * (rows - gray[1]) + "px";

    var graylen = gray.length / 2;
    if (graylen > 1) {
        for (var i = 1; i < graylen; i++) {
            var clonedgray = document.createElement('img');
            clonedgray.src = graypos.src;
            clonedgray.classList.add('gray');
            clonedgray.style.display = "block";
            clonedgray.style.left = -graywidth + cellwidth * gray[i * 2] + "px";
            clonedgray.style.top = -grayheight + cellheight * (rows - gray[i * 2 + 1]) + "px";
            mapgrid.appendChild(clonedgray);
        }
    }

}

//drawUnit*********************************************************************************
//drawHC*********************************************************************************
function drawHC(mapdata, colorBlob, hazard) {
    cols = mapdata[0][0];
    rows = mapdata[0][1];

    var mapgrid = document.getElementById("myGrid");
    var cellwidth = mapgrid.offsetWidth / cols;
    var cellheight = mapgrid.offsetHeight / rows;

    var colorBlobpos = document.getElementById("colorBlob");
    colorBlobwidth = colorBlobpos.offsetWidth / 2;
    colorBlobheight = colorBlobpos.offsetHeight / 2;

    var colorBloblen = colorBlob.length / 2;
    if (colorBloblen >= 1) {
        for (var i = 0; i < colorBloblen; i++) {
            var clonedcolorBlob = document.createElement('img');
            clonedcolorBlob.src = colorBlobpos.src;
            clonedcolorBlob.classList.add('colorBlob');
            clonedcolorBlob.style.display = "block";
            clonedcolorBlob.style.left = -colorBlobwidth + cellwidth * colorBlob[i * 2] + "px";
            clonedcolorBlob.style.top = -colorBlobheight + cellheight * (rows - colorBlob[i * 2 + 1]) + "px";
            mapgrid.appendChild(clonedcolorBlob);
        }
    }

    var hazardpos = document.getElementById("hazard15");
    hazardwidth = hazardpos.offsetWidth / 2;
    hazardheight = hazardpos.offsetHeight / 2;

    var hazardlen = hazard.length / 2;
    if (hazardlen >= 1) {
        for (var i = 0; i < hazardlen; i++) {
            var clonedhazard = document.createElement('img');
            clonedhazard.src = hazardpos.src;
            clonedhazard.classList.add('hazard');
            clonedhazard.style.display = "block";
            clonedhazard.style.left = -hazardwidth + cellwidth * hazard[i * 2] + "px";
            clonedhazard.style.top = -hazardheight + cellheight * (rows - hazard[i * 2 + 1]) + "px";
            mapgrid.appendChild(clonedhazard);
        }
    }

}

//drawHC*********************************************************************************
//drawPath*********************************************************************************
function drawPath(mapdata, path) {
    //var path = [(3, 1), (4, 1), (5, 1), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5)];
    var elements = document.getElementsByClassName('path');
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.display = 'none';
    }

    var patharr = path.replace(/[^\d\s]/g, '').trim().split(/\s+/).map(Number);
    cols = mapdata[0];
    rows = mapdata[1];

    var mapgrid = document.getElementById("myGrid");
    var cellwidth = mapgrid.offsetWidth / cols;
    var cellheight = mapgrid.offsetHeight / rows;

    var pathpos = document.getElementById("path");
    pathpos.style.display = "block";
    pathwidth = pathpos.offsetWidth / 2;
    pathheight = pathpos.offsetHeight;
    pathpos.style.left = -pathwidth + cellwidth * patharr[0] + "px";
    pathpos.style.top = -pathheight + cellheight * (rows - patharr[1]) + "px";

    var pathlen = patharr.length / 2;
    if (pathlen > 1) {
        for (var i = 1; i < pathlen - 1; i++) {
            var clonedpath = document.createElement('img');
            clonedpath.src = pathpos.src;
            clonedpath.classList.add('path');
            clonedpath.style.display = "block";
            clonedpath.style.left = -pathwidth + cellwidth * patharr[i * 2] + "px";
            clonedpath.style.top = -pathheight + cellheight * (rows - patharr[i * 2 + 1]) + "px";
            mapgrid.appendChild(clonedpath);
        }
    }
}

//drawPath*********************************************************************************