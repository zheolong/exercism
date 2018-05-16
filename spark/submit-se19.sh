/Users/qiaojunlong/Github/spark/bin/spark-submit \
    --class 'SimpleApp' \
    --master 'spark://se19.adsys.zzbc2.qihoo.net:7077' \
    --executor-memory 125m \
    --total-executor-cores 1 \
    target/scala-2.10/simple-project_2.10-1.0.jar
