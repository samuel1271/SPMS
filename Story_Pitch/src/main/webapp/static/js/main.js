let baseUrl = '/Story_Pitch';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();

function setNav(){
    nav.innerHTML = ` 
        <a href="index.html" class" = "menuScreen"><strong>Home</strong></a>
        <a href="viewStories.html" class = "menuScreen" id = "viewStoriesTab">Stories</a>
    `;

    if (!loggedUser){
        nav.innerHTML += `
            <label for="login_email" class="login">Email: </label>
            <input id="login_email" class="login" type="text" />
            <label for="pass" class="login"> Password: </label>
            <input id="pass" type="password"  class="login"/>
            <button type="button" id="loginBtn" class="login">Log In</button>
            <button type="button" id="RegisterBtn" class="login"><a href="registration.html" >Register</a></button>

        `;
    } else {
        nav.innerHTML += `
            <a href="myStories.html" class = "menuScreen">My Stories</a>
            <span>
                <a href="profile.html" class="login">${loggedUser.email}&nbsp;</a> 
                <button type = "button" id = "loginBtn" class="login">LogOut</button>
            </span>        
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if(loggedUser){
        loginBtn.onclick = logout;
    }else{
        loginBtn.onclick = login;
    }

}

async function login(){
    let url = baseUrl + '/user/login?';
    url += 'email=' + document.getElementById('login_email').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'POST'});

    switch(response.status){
        case 200: //succes
            loggedUser = await response.json();
            setNav();
            break;
        case 400: //incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value='';
            break;
        case 404: //user not found
            alert('That user does not exist.');
            document.getElementById('login_email').value = '';
            document.getElementById('pass').value = '';
            break;
        default: //other error
            alert('Something went wrong.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/user/login';
    let response = await fetch(url, {method:'DELETE'});
    loggedUser = null;
    if (response.status != 200) alert('Something went wrong.');
    setNav();
}

async function checkLogin() {
    let url = baseUrl + '/user';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();   
    setNav();
}

async function userUpdate(){
    let url = baseUrl + '/user';
    url += loggedUser.id;
    let response = await fetch(url, {method: 'PUT',body:JSON.stringify(loggedUser)});
    if (response.status === 200){
        alert('Person Updated');
    }else{
        alert('Something went wrong');
    }

}