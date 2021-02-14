fetch("https://13.76.132.107:8081/memes").then(
    res => {
        res.json().then(
            data=> {
                if (data.length > 0) {

                    var memeData = "<thead> <tr> <th>Name</th> <th>Caption</th> <th>URL</th> </tr> </thead>";
                        data.forEach((meme)=>{
                            memeData += "<tr> <td>"+ meme.name +"</td>" + "<td>"+ meme.caption +"</td>" + "<td>"+ meme.url +"</td> </tr>"
                        })

                        document.getElementById("table-body").innerHTML = memeData;
                    }
            }
        )
    }
)