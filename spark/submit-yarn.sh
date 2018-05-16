#/Users/qiaojunlong/Applications/spark-1.4.1-bin-hadoop2.6/bin/spark-submit \
#/Users/qiaojunlong/Applications/spark-1.5.2-bin-hadoop2.6/bin/spark-submit \
#/Users/qiaojunlong/Github/spark/bin/spark-submit \
/Users/qiaojunlong/Applications/spark-1.6.0-SNAPSHOT-bin-custom-spark/bin/spark-submit \
    --class 'SimpleApp' \
    --master "yarn-cluster" \
    --deploy-mode cluster \
    --executor-memory 1000m \
    --total-executor-cores 1 \
    target/scala-2.10/simple-project_2.10-1.0.jar
