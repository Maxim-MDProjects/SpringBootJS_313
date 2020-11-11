async function getUsers() {
    let jobs = [];
   // for(let name of names) {
        //let job = fetch(`https://api.github.com/users/${name}`).then(
        let job = fetch(`http://localhost:8089/adminrest/edit/1`).then(
            successResponse => {
                if (successResponse.status != 200) {
                    return null;
                } else {
                    return successResponse.json();
                }
            },
            failResponse => {
                return null;
            }
        );
        jobs.push(job);
    //}

    let results = await Promise.all(jobs);

    return results;
}

console.log(getUsers())