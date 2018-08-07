import Config from '../config';
import axios from 'axios';
import { toast } from 'react-toastify';

class Api {

  get(uri,_cb,_err) {
        var url = Config.api + uri;
        axios.get( url, {headers: {}})
            .then(function(response) {
                _cb(response)
            }).catch(function (error) {
                console.log(error);
                if ( _err ) {
                    _err(error);
                    return;
                }
                toast.error(r => "Error " + error )
            });
  }

  post(uri,body,_cb,_err) {
        var url = Config.api + uri;
        axios.post(
            url,
            body,
            {
                headers: {
                            'Content-Type': 'application/json',
                        }
            })
            .then(function(response) {
                _cb(response)
            }).catch(function (error) {
                if ( _err ) {
                    _err(error);
                }
                console.log(error);
//                {"timestamp":"2018-08-07T02:02:35.202+0000","status":500,"error":"Internal Server Error","message":"You must specify at least one indexed query field","path":"/docs/search"}
              toast.error(r => "Error " + error )
            });
  }
}

var instance = new Api();

export default instance;