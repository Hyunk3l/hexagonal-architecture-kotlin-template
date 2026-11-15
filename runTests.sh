
copier copy -f --vcs-ref HEAD . ../hexagonal-architecture-kotlin-template-test && \
  cd ../hexagonal-architecture-kotlin-template-test && \
  test ! -e skills && \
  ./gradlew clean build --info && \
  cd ../ && \
  rm -r hexagonal-architecture-kotlin-template-test/
