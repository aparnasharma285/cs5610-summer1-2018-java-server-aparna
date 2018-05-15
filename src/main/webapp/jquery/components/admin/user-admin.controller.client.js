(function () {

    $(main);
    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {

        tbody = $('tbody');
        template = $('.wbdv-template');
        $('.wbdv-create').css('cursor', 'pointer');
        $('.wbdv-update').css('cursor', 'pointer');
        $('.wbdv-search').css('cursor', 'pointer');
        $('.wbdv-create').click(createUser);
        $('.wbdv-update').click(updateUser);
        $('.wbdv-search').click(searchUser);
        findAllUsers();
    }


    function createUser() {


        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        resetForm();


        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService.createUser(user).then(findAllUsers);
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
            clone.attr('id', user.id);
            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(renderUser);
            tbody.append(clone);

        }
    }

    function deleteUser(event) {

        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function renderUser(event) {

        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .findUserById(userId)
            .then(function (user) {
                $('#userIdFld').val(userId);
                $('#usernameFld').val(user.username);
                $('#passwordFld').val(user.password);
                $('#firstNameFld').val(user.firstName);
                $('#lastNameFld').val(user.lastName);
                $('#roleFld').val(user.role);
                $('#emailFld').val(user.email);
                $('#phoneFld').val(user.phone);
                $('#dobFld').val(user.dateOfBirth);
            });
    }

    function updateUser() {

        var userId = $('#userIdFld').val();
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var email = $('#emailFld').val();
        var phone = $('#phoneFld').val();
        var dob = $('#dobFld').val() ;


        $('#userIdFld').val('');
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#emailFld').val('');
        $('#phoneFld').val('');
        $('#dobFld').val('');
        $('#roleFld').val('Faculty');

        var user = {

            id: userId,
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phone: phone,
            dateOfBirth: dob,
            role: role
        };

        userService.updateUser(user, userId).then(findAllUsers);
    }


    function searchUser() {

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        resetForm();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService.searchUser(user)
            .then(renderUsers)
            .catch(function (reason) {
                console.log("couldnt find");
                return;
            });

    }

    function resetForm() {
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#roleFld').val('Faculty');

    }
})();