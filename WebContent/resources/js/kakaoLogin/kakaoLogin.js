
    //발급받은 키 중 javascript키를 사용해준다.
    Kakao.init('d1d34875f932e0d2170724f5bac7d160'); 
    Kakao.isInitialized()
    // sdk초기화여부판단
    console.log(Kakao.isInitialized()); 

    function kakaoLogin() {
       
        Kakao.Auth.login({
          success: function (res) {
            Kakao.API.request({
              url: '/v2/user/me',
              success: function (res) {
                console.log(res)
                let userId = res.id;
                let userNickName = res.properties.nickname;

                console.log("userId",userId)
                console.log("userNickName",userNickName)

                fetch("/member/kakaoLogin?userId="+userNickName)
                .then(res => {
                    if(res.ok){
                        pageChange();
                        return res.text();
                    } else {
                        throw new Error(res.status);
                    }
                })
              },
              fail: function (error) {
                console.log(error)
              },
            })
          },
          fail: function (error) {
            console.log(error)
          },
        })

      }

      function pageChange() {
        setTimeout(() => {
            location.href='/index';    
        }, 500);
        
      }

      function kakaoLogout() {
        if (Kakao.Auth.getAccessToken()) {
          Kakao.API.request({
            url: '/v1/user/unlink',
            success: function (response) {
                console.log(response)
            },
            fail: function (error) {
              console.log(error)
            },
          })
          Kakao.Auth.setAccessToken(undefined)
        }
      }  