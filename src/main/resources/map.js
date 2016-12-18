/**
 * Created by hector on 17.12.16.
 */
function() {
    var year = parseInt(this.air_date.substring(0, 4));
    emit(year, 1);
}