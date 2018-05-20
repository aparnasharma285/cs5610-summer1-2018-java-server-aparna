(function () {

    $(main);
    var userService = new UserServiceClient();

    function main() {
        var loginForm = $('.wbdv-loginForm');
        $('.wbdv-login').click(login);
        $('wbdv-resetPassword').click(resetPassword);
        $('.alert').toggle();
    }

    function login() {
        $('.alert').hide();
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();

        if (username.length < 1 || password.length < 1) {
            $('.wbdv-emptyField').show();
            $('.wbdv-emptyField').html('Username or Password field cannot be empty');

        } else {
            var user = {
                username: username,
                password: password
            }
            userService.login(user).then(function (response) {
                if (response.status == 404) {
                    $('.wbdv-invalidCredentials').show();
                    $('.wbdv-invalidCredentials').html('Invalid Credentials');
                } else {
                    userService.findUserByUsername(user, username)
                        .then(function (results) {
                            return results.json();
                        })
                        .then(function (user) {
                            window.location.href ="/jquery/components/profile/profile.template.client.html";
                        })
                }

            })
        }
    }

    function resetPassword() {

        var emailId = $('#emailFld').val();

        userService.resetEmail(emailId);
    }
})();