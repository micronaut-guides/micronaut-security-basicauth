FROM oracle/graalvm-ce:19.2.1 as graalvm
COPY . /home/app/micronautguide
WORKDIR /home/app/micronautguide
RUN gu install native-image
RUN native-image --no-server -cp build/libs/completegraal-0.1-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronautguide .
ENTRYPOINT ["./micronautguide"]
