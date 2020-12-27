const DepositAccount = artifacts.require("DepositAccount");

module.exports = function(deployer) {
    deployer.deploy(DepositAccount);
};
