(function () {

        $(main);
        var userService = new UserServiceClient();

        function main() {

            var profileInfo = $('.wbdv-profile');

            userService.getCurrentUser().then( function (user) {
                populateForm(user.id);
            });


            $('.wbdv-updateProfile').click(updateProfile);
            $('.wbdv-logout').click(logout);
            $('.wbdv-updateSuccessful').toggle();
        }

        function updateProfile() {
            $('.wbdv-updateSuccessful').hide();
            var userId = $('#userIdFld').val();
            var username = $('#usernameFld').val();
            var password = $('#passwordFld').val();
            var firstName = $('#firstNameFld').val();
            var lastName = $('#lastNameFld').val();
            var role = $('#roleFld').val();
            var phone = $('#phoneFld').val();
            var email = $('#emailFld').val();
            var dob = $('#datepicker').val();

            var user = {
                id: userId,
                username: username,
                password: password,
                firstName: firstName,
                lastName: lastName,
                role: role,
                dateOfBirth: dob,
                email: email,
                phone: phone
            }

            userService.updateUser(user, userId).then(
                populateForm(userId)).then(function (value) {
                $('.wbdv-updateSuccessful').show();
                $('.wbdv-updateSuccessful').html('Profile Updated');
            });
        }

        function logout() {

            userService.logOut();
            window.location.href = "/jquery/components/login/login.template.client.html";
        }

        function populateForm(userId) {
            userService.findUserById(userId)
                .then(function (user) {
                    $('#userIdFld').val(userId);
                    $('#usernameFld').val(user.username);
                    $('#passwordFld').val(user.password);
                    $('#emailFld').val(user.email);
                    $('#phoneFld').val(user.phone);
                    $('#roleFld').val(user.role);
                    $('#datepicker').val(user.dateOfBirth);
                })
        }
    }


)();