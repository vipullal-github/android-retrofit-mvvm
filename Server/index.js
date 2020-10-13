const { request } = require('express');


var express = require('express'),
    app = express(),
    port = process.env.PORT || 3000;
app.use(express.json());
app.listen(port);

console.log('RESTful API server started on: ' + port);


let personsList = [];

personsList.push( {
    "givenName":"Rajendra", "familyName":"Prasad","id":1
});

personsList.push( {
    "givenName":"Sarvepalli", "familyName":"Radhakrishnan","id":2,
});

personsList.push(       {
    "givenName":"Zakir", "familyName":"Husain","id":3 
});

var mNextPersonId = personsList.length + 1;

function generateJSON(){
    var myObj = {
        persons: []
    };
    personsList.map(  function( aPerson ){
    myObj.persons.push({
        "givenName": aPerson.givenName,
        "famiyName": aPerson.familyName,
        "id": aPerson.id
        });
    });
    return myObj;
}


//--------------------------------------------
// curl -v http://localhost:3000/persons
app.get("/persons", (req, res, next) => {
    //res.json(["Tony","Lisa","Michael","Ginger","Food"]);
    var retValue = generateJSON();
    console.log("Headers.host = "+ req.headers.host );
    console.log("\nSending response\n" + retValue );
    res.send( retValue );
    console.log("\nReady to receive more requests\n")
    }
);



// --------------------------------------------------------
//curl -v -X PUT -H "Content-Type: application/json" -d '{"givenName":"V.V","familyName":"Giri"}'  http://localhost:3000/persons
app.put( "/persons", (req,res,next) =>{
    if( req.body ){
        var aPerson = req.body;
        var retStatus = addPerson( aPerson );
        res.send( retStatus );
    }
    else{
        res.send("Incorrect request\n");
    }
});

function addPerson( aPerson ){
    if( aPerson.givenName && aPerson.familyName ){
        aPerson.id = mNextPersonId;
        ++mNextPersonId;
        personsList.push( aPerson );
        return (`{ newId: ${aPerson.id}}\n`);
    }
    return "Incorrect request: Missing fields";
}

// --------------------------------------------------------
app.delete("/persons", (req, resp, next){
    
});




