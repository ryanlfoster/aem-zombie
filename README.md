# lodges

## Development environment setup

### Install virtualbox

Download and install the appropriate package for your platform:

https://www.virtualbox.org/wiki/Downloads

### Install vagrant

Download and install the appropriate package for your platform:

https://www.vagrantup.com/downloads.html

NOTE: Version 1.5+ is required

### Install puppet

Follow the instructions to install Puppet for your platform:

http://docs.puppetlabs.com/guides/installation.html

Note: Version 3.x is required

### Install required vagrant plugins

Execute the following commands from the cli:

    vagrant plugin install vagrant-hostmanager

### Install python + pip + virtualenvwrapper

#### OSX:

#### Windows:

#### Linux:
    sudo apt-get install python python-pip virtualenvwrapper

### Install the BPD tool:

Create and activate a virtual environment:

    mkvirtualenv vagrant

Install the tool:

    pip install -e git+git@github.com:natgeo/bpd-tool.git@master#egg=bpd-tool

### Configure keys for SSH access:

Add the following lines to your ~/.ssh/config file:

    Host *.dev
        User webapp
        IdentityFile ~/.vagrant.d/insecure_private_key

### Launch the VM

From the folder where this README lives in, execute the following command:

    vagrant up

### Provision the VM

From the folder where this README lives in, execute the following command:

    fab provision_all
