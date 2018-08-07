
var cfg = {};

if (/localhost/.test(window.location.href)) {
    cfg =  {
        api: 'http://localhost:8080',
        env: 'LOCAL'
    };
}

if (/poc/.test(window.location.href)) {
    cfg =  {
        api: 'https://poclb.testnix.com',
        env: 'DEV'
    };
}

console.log("cfg is " + JSON.stringify(cfg,0,4))
export default ( cfg );
