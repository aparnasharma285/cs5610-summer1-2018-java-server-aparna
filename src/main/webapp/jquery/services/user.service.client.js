function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    /*this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;*/
    this.url = 'http://localhost:8080/api/user';
    var self = this;
    var createUrl;

    function createUser(user, role) {

        if (role == "Student") {
            createUrl = "http://localhost:8080/api/student";
        } else if (role == "Faculty") {

            createUrl = "http://localhost:8080/api/faculty";
        } else {
            createUrl = "http://localhost:8080/api/user";
        }


        return fetch(createUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });

    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });

    }
}