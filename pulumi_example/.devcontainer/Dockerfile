FROM pulumi/pulumi-nodejs:2.15.6-debian

RUN apt-get update && \
    apt-get install -y zsh wget

RUN wget https://github.com/robbyrussell/oh-my-zsh/raw/master/tools/install.sh -O - | zsh || true

CMD ["zsh"]