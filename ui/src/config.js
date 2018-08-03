
console.log("window.location = " + window.location);

var hackFig = {};

if (/localhost/.test(window.location.href)) {
    hackFig =  {
        api: 'http://localhost:8080',
        env: 'LOCAL'
    };
}

if (/dev-www/.test(window.location.href)) {
    hackFig =  {
        api: 'https://dev-www.wodger.com.au',
        env: 'DEV'
    };
}

export default ( hackFig );
