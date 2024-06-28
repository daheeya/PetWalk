document.addEventListener("DOMContentLoaded", function () {
    const status = document.querySelector("#status");
    const mapLink = document.querySelector("#map-link");
    const findMeButton = document.querySelector("#find-me");
    const endWalkButton = document.querySelector("#end-walk");
    const savePathButton = document.querySelector("#save-path");

    var map = new kakao.maps.Map(mapContainer, mapOption);

    function geoFindMe() {
        mapLink.href = "";
        mapLink.textContent = "";

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

            const walkDto = {
                calories: 0,
                distance: 0,
                startWalkTime: new Date().toISOString(),
                stepCount: 0,
                petIdx: 1, // 예시 값
                userIdx: 1 // 예시 값
            };

            fetch('/walks/start', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(walkDto),
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    //TODO : data.walkIdx = undefined 문제 해결하기
                    localStorage.setItem('walkId', data.walkIdx); // Walk ID 저장
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

            findMeButton.style.display = "none"; // 산책 시작 버튼 숨기기
            endWalkButton.style.display = "inline-block"; // 산책 종료 버튼 보이기
            savePathButton.style.display = "inline-block"; // 경로 저장 버튼 보이기
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

    function endWalk() {
        const walkId = localStorage.getItem('walkId');
        const walkDto = {
            calories: 100, // 예시 값
            distance: 1.5, // 예시 값
            endWalkTime: new Date().toISOString(),
            stepCount: 2000 // 예시 값
        };

        fetch(`/walks/end/${walkId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(walkDto)
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(error => { throw new Error(JSON.stringify(error)); });
                }
                return response.json();
            })
            .then(data => {
                console.log('Walk ended:', data);
                localStorage.removeItem('walkId'); // Walk ID 제거
                findMeButton.style.display = "inline-block"; // 산책 시작 버튼 보이기
                endWalkButton.style.display = "none"; // 산책 종료 버튼 숨기기
                savePathButton.style.display = "none"; // 경로 저장 버튼 숨기기
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    function savePath() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                const pathDto = {
                    latitude: latitude,
                    longitude: longitude,
                    walkIdx: localStorage.getItem('walkId')
                };

                fetch('/walks/path', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(pathDto)
                })
                    .then(response => {
                        if (!response.ok) {
                            return response.json().then(error => { throw new Error(JSON.stringify(error)); });
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log('Path saved:', data);
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            });
        } else {
            console.error("Geolocation is not supported by this browser.");
        }
    }

    findMeButton.addEventListener("click", geoFindMe);
    endWalkButton.addEventListener("click", endWalk);
    savePathButton.addEventListener("click", savePath);

    // 처음 로드 시 버튼 초기화
    endWalkButton.style.display = "none"; // 산책 종료 버튼 숨기기
    savePathButton.style.display = "none"; // 경로 저장 버튼 숨기기
});
