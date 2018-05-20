function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserByUsername = findUserByUsername;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.resetEmail = resetEmail;
    this.updateUser = updateUser;
    this.searchUser = searchUser;
    this.register = register;
    this.login = login;
    this.logout = logout;
    this.getCurrentUser = getCurrentUser;

    this.url = '/api/user';
    this.resetUrl = '/api/reset';
    this.searchUrl = '/api/search/user';
    this.registerUrl = '/api/register';
    this.loginUrl = '/api/login';
    this.profile = '/api/profile';
    this.sessionInvalidate = '/api/session/invalidate';
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

    function resetEmail(emailId) {
        return fetch(self.resetUrl + '/' + emailId)
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
            credentials : 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });

    }

    function findUserByUsername(user, username) {
        return fetch(self.searchUrl + '/' + username);
    }

    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            credentials : 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function getCurrentUser(){

        return fetch(self.profile, {method: 'get',
            credentials : 'same-origin'}).then(function (response) {
            return response.json();
        })
    }
    
    
    function logout() {

        return fetch(self.sessionInvalidate);
        
    }
}