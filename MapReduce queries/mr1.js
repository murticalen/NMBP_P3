var map = function() {
    var count = 0;
	if (this.comments !== undefined)
            for(com in this.comments){
                count++;
            }
        if(count != 0)
            emit(0, {id : this._id, title: this.title, count : count});
};

var reduce = function(key, values) {
    result = {};
    values = values.sort(function(a,b){
        return b.count-a.count;
    });
    for(var i = 0; i < values.length; i++){
        result[i] = values[i];
    }
    return result;
};

var fin = function(key, reducedVal){
    
}

db.news.mapReduce(
	map,
	reduce,
	{out: "mr1_news"}
);
//db.mr1_news.find().sort({value : -1});

db.mr1_news.aggregate(
   [
     { $sort: { "value.count": -1 } }
   ]
)