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

        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#roleFld').val('Faculty');


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
            clone.attr('id', user.id);
            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(editUser);
            tbody.append(clone);

        }
    }

    function deleteUser(event) {

        console.log(event);
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

    function editUser(event) {

        console.log(event);
    }
})();