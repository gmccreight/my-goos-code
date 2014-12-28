# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.box = "trusty64vagrant"
  config.vm.box_url = "https://cloud-images.ubuntu.com/vagrant/trusty/current/trusty-server-cloudimg-amd64-vagrant-disk1.box"
  config.vm.synced_folder "./", "/goos"

  if ENV["USE_ACTUAL_X11"] == "1"
    # This enables you to run the end-to-end tests and see what is happening in
    # your host.  See the "Debugging (on Mac Yosemite)" section in the README.md
    # To use this, you have to start vagrant like so:
    # USE_ACTUAL_X11=1 vagrant up
    config.ssh.forward_x11 = true
  end

end