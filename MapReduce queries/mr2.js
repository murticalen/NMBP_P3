var map = function() {  
    if (this.review !== undefined) { 
        var splitted = this.review.toLowerCase().split(" "); 
        for (var i = 0; i < splitted.length; i++) {              
            emit(this.author, splitted[i]); 
        }
    }
};

var reduce = function(key, values) {
    var temp = { };
    var result = {};
    for (var i = 0, j = values.length; i < j; i++) {
       temp[values[i]] = (temp[values[i]] || 0) + 1;
    }
    var pos = 0;
    for(var word in temp){
        if(pos < 10){
            result[pos] = [word, temp[word]];
            pos++;
        }
        else{
            if(result[9][1] < temp[word]){
                for(var j = 0; j < 10; j++){
                    if(result[j][1] < temp[word]){
                        result[j] = [word, temp[word]];
                        break;
                    }
                }
            }
        }
        i++;
    }
    return result;
}; 

db.news.mapReduce(map, reduce, {out: "mr2_news"})

db.mr2_news.find()