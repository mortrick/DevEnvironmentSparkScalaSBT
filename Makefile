start:
	docker-compose  build --no-cache && docker-compose up --scale spark-worker=1