FROM nginx:alpine
ADD nginx.conf /etc/nginx/nginx.conf
RUN rm -rf /usr/share/nginx/html/*
ADD build /usr/share/nginx/html
EXPOSE 3000 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]