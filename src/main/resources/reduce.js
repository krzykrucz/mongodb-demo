/**
 * Created by hector on 17.12.16.
 */
function(key, values) {
    var sum=0;
    for(var i in values) {
        sum += values[i];
    }
    return sum;
}