function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserByUsername = findUserByUsername;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.searchUser = searchUser;
    this.register = register;
    this.login = login;
    this.url = 'http://localhost:8080/api/user';
    this.searchUrl = 'http://localhost:8080/api/search/user';
    this.registerUrl = 'http://localhost:8080/api/register';
    this.loginUrl ='http://localhost:8080/api/login';
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

    function updateUser(user, userId) {


        return fetch(self.url + '/' + userId, {
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

        return fetch(self.searchUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });

    }

    function register(user) {
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });

    }

    function findUserByUsername(user, username) {
        return fetch(self.searchUrl+'/'+username);
    }
    
    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}