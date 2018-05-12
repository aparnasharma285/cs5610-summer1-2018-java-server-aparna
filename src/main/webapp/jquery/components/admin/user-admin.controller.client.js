(function () {

    jQuery(main);

    var tbody;
    var createUserForm;

    function main() {

        tbody = $('tbody');
        createUserForm = $('.wbdv-form');
        $('.wbdv-create').click(createUser);
    }


    function createUser() {


        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var createUrl;

        var user = {

            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };


        if (role == "Student") {
            createUrl = "http://localhost:8080/api/student";
        } else if (role == "Faculty") {

            createUrl = "http://localhost:8080/api/faculty";
        } else {
            createUrl = "http://localhost:8080/api/user";
        }


        fetch(createUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });

    }
})();