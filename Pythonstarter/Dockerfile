FROM python:2

WORKDIR /

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

COPY start.sh /start.sh

EXPOSE 8000

CMD ./start.sh
