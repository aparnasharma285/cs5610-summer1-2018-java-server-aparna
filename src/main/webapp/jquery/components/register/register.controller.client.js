(function () {

    $(main);
    var userService = new UserServiceClient();

    function main() {

        var registerForm = $('.card-body');
        $('.wbdv-register').click(register);
        $('.alert').toggle();
    }

    function register() {
        $('.alert').hide();
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var verifyPassword = $('#verifyPasswordFld').val();


        if (password == verifyPassword) {

            var user = {
                username: username,
                password: password
            }

            userService.register(user)
                .then(function (response) {
                    if (response.status == 409) {
                        $('.alert-primary').show();
                        $('.alert-primary').html('Username already exists');
                    } else {
                        $('.alert-info').show();
                        $('.alert-info').html('Username registered');
                    }
                }, function (error) {
                    $('.alert-primary').show();
                    $('.alert-primary').html('Some error occured');
                });

        } else {
            $('.alert-primary').show();
            $('.alert-primary').html('Password doesnt match');
        }


    }
})();