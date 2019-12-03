FROM oracle/graalvm-ce:19.3.0-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.0-java11 as graalvm # For JDK 11
COPY . /home/app/micronautguide
WORKDIR /home/app/micronautguide
RUN gu install native-image
RUN yum install -y libstdc++-static
RUN native-image --no-server --static -cp build/libs/complete-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronautguide/micronautguide /app/micronautguide
ENTRYPOINT ["/app/micronautguide","-Xmx68m"]
