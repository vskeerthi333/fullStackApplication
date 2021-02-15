fetch("https://13.76.132.107:8081/memes").then(
    res => {
        res.json().then(
            data=> {


                if (data.length > 0) {

                    var memeData = "<thead> <tr> <th>Name</th> <th>Caption</th> <th>URL</th> </tr> </thead>";
                        data.forEach((meme)=>{
                            var wrapperDiv = document.createElement('div');
                            wrapperDiv.id = 'block';
                            wrapperDiv.className = 'block';

                            var title = document.createElement('h4');
                            var text = document.createTextNode("Created By : " + meme.name);

                            var caption = document.createElement('h5');
                            var captionText = document.createTextNode("Caption : " + meme.caption);

                            var title

                            title.appendChild(text)
                            caption.appendChild(captionText);

                            var img = document.createElement('img');
                            img.src = meme.url;

                            wrapperDiv.appendChild(title);
                            wrapperDiv.appendChild(caption);
                            wrapperDiv.appendChild(img);
                            var parent = document.getElementById("fetched-data");

                            parent.appendChild(wrapperDiv);

                            //memeData += "<tr> <td>"+ meme.name +"</td>" + "<td>"+ meme.caption +"</td>" + "<td>"+ meme.url +"</td> </tr>"
                        })

                    }
            }
        )
    }
)