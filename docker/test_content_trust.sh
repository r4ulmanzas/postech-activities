# See https://docs.docker.com/engine/security/trust/ for a complete explanation

pull_image_with_content_trust() {
    export DOCKER_CONTENT_TRUST=$1
    docker pull iron/node
}

echo "Trying to pull image with DOCKER_CONTENT_TRUST=1"
pull_image_with_content_trust 1

# echo "Trying to pull image with DOCKER_CONTENT_TRUST=0"
# pull_image_with_content_trust 0
