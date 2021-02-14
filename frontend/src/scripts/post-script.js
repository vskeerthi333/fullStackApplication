window.onload = function() {
    var postbutton = document.getElementById("post-btn");

    postbutton.addEventListener("click", function() {
        var name = document.getElementById("name").value;
        var caption = document.getElementById("caption").value;
        var url = document.getElementById("url").value;

        fetch("http://13.76.132.107:8081/memes", {
            method : 'POST',
            body : JSON.stringify({
                "name" : name,
                "caption" : caption,
                "url" : url
            }),
            headers:{
                "Content-Type" : "application/json; charset=UTF-8"
            }
        })

        location.reload();
        return false;
    })



}
