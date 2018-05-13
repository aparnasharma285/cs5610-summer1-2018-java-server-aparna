function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.searchUser = searchUser;
    this.url = 'http://localhost:8080/api/user';
    var searchUrl = 'http://localhost:8080/api/search/user';
    var self = this;

    function createUser(user) {

        return fetch(self.url, {
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

    function deleteUser(userId) {

        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function updateUser(user) {


        return fetch(self.url, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function (response) {
                if (response.bodyUsed) {
                    return response.json();
                } else {
                    return null;
                }
            });

    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function searchUser(user) {

        return fetch(searchUrl,{
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
                return response.json();
        });

    }
}