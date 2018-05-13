(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient();


    function main() {

        tbody = $('tbody');
        template = $('.wbdv-template');
        $('.wbdv-create').click(createUser);

        findAllUsers();
    }


    function createUser() {


        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {

            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService.createUser(user, role).then(findAllUsers);
    }

    function findAllUsers() {
        userService.findAllUsers()
            .then(renderUsers);
    }

    function renderUsers(users) {

        tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            tbody.append(clone);

        }
    }
})();