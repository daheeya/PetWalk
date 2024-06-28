function geoFindMe() {
    const status = document.querySelector("#status");
    const mapLink = document.querySelector("#map-link");

    mapLink.href = "";
    mapLink.textContent = "";

    var map = new kakao.maps.Map(mapContainer, mapOption);

    function success(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        status.textContent = "";
        mapLink.href = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
        mapLink.textContent = `위도: ${latitude} °, 경도: ${longitude} °`;

        var locPosition = new kakao.maps.LatLng(latitude, longitude); // 사용자의 현재 위치 좌표
        map.setCenter(locPosition); // 지도 중심을 사용자의 현재 위치로 변경합니다

        // 현재 위치에 표시할 마커
        var marker = new kakao.maps.Marker({
            // 현재위치에 마커를 생성합니다
            position: locPosition
        });
        // 지도에 마커를 표시합니다
        marker.setMap(map);

        // pathDto 객체 생성
        const pathDto = {
            latitude: latitude,
            longitude: longitude
        };

        // Send AJAX request to server
        fetch('/saveLocation', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pathDto)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    function error() {
        status.textContent = "현재 위치를 가져올 수 없음";
    }

    if (!navigator.geolocation) {
        status.textContent = "브라우저가 위치 정보를 지원하지 않음";
    } else {
        status.textContent = "위치 파악 중…";
        navigator.geolocation.getCurrentPosition(success, error);
    }
}

document.querySelector("#find-me").addEventListener("click", geoFindMe);
