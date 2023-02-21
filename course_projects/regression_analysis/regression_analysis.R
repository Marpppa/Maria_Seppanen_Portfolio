## Take Home Task
## Regression Analysis
## Autumn 2021
## Maria Seppänen

## 1.
cruise <-read.fwf("cruise.txt",width=c(20,20,rep(8,7)),col.names=c("ship",
"cline","age","tonnage","passengers","length","cabins","passdens","crew"))
attach(cruise)


# Looking at the whole data. There seems to be correlation between many variables,
# but not ship and cline.
plot(cruise)

# Correlation matrix. Variable "passengers" does not seem to be correlated with the others.
round(cor(cruise[,3:9]))

par(mfrow=c(3,2))
hist(age, col = 1)
hist(tonnage, col = 2)
hist(length, col = 4)
hist(cabins, col = 5)
hist(passdens, col = 6)
hist(crew, col = 7)

mini <- lm(crew ~ age)
lm.cruise <- lm(crew ~ age+tonnage+length+cabins+passdens)
lm.cruise <- step(lm.cruise) # crew ~ length + cabins + passdens is best
res <- summary(lm.cruise)$residuals

par(mfrow = c(2,2))
plot(res);abline(h=0) # Looks okay.
plot(length, res);abline(h=0)
plot(cabins, res);abline(h=0)
plot(passdens, res);abline(h=0)
# Residual plots look fine.

# Checking the normality assumption.
par(mfrow=c(1,1))
qqnorm(res)
qqline(res)
# Plot looks ok, but there are three bad observations and a bit of a curve 
# in the beginning.

## 2.

# a)
heifeng <-read.table("C:/Users/35845/Desktop/Regression analysis/heifeng.txt",header=TRUE)
attach(heifeng)
lm.soy <- lm(log(Area)~ log(Length)+log(Width))
summary(lm.soy) # fitted Y = 0.50408 + 1.25542*log(Length) + 0.72601*log(Width)

## b)
# Testing the null hypothesis H0: B_0=B_1=B_2=0
an.soy <- anova(lm.soy)
SSR <- sum(an.soy$Sum)
SSE <- an.soy$Sum[3]

# MSR/MSE
F <- (SSR/1)/(SSE/145)
qf(0.95, 1, 145) # 3.906 < F, which is why H0 is rejected.
# p-value
1-pf(1607.404, 1, 145) # so small, it is marked as zero

## c) 

an.soy # Is significant, based on the F and p values for variable log(Width)

## d) 

s.soy <- summary(lm.soy)
# B1-B2
B12 <- s.soy$coefficients[2]-s.soy$coefficients[3]

# sd for B1-B2
sd2 <- s.soy$coefficients[3,2]
sd1 <- s.soy$coefficients[2,2]
sd12 <- sqrt(sd1^2-sd2^2)

# CI for B1-B2 ~ N(mu2-mu1, sd12^2)

B12 - qt(0.975,145)*sd12
B12 + qt(0.975,145)*sd12
# [0.4802637, 0.5785557]
