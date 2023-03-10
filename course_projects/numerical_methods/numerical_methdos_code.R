## Laskennalliset menetelm?t ja bayesilaisuuden perusteet
## Syksy 2020
## HARJOITUSTY?

# Tuodaan data.
library("readxl")
data1 <- read_excel("C:\\Users\\35845\\Desktop\\LAME\\HARJOITUSTY?\\Tre_myydyt_kaksiot_2016.xlsx", sheet = "Taul1")
data2 <- read_excel("C:\\Users\\35845\\Desktop\\LAME\\HARJOITUSTY?\\Tre_myydyt_kaksiot_2016.xlsx", sheet = "Taul2")

data <- as.data.frame(data1)
attach(data)

data <- as.matrix(data); data <- as.data.frame(data)
M <- matrix(data1$Neli?t, data1$Neli?hinta, ncol = 2, nrow = 564)
plot(M)

# Dataaaaaaaa...
M2 <- data.frame(data1$Neli?hinta, data1$Kaupunginosa)
barplot(M2)
K <- data.frame(data1$Kaupunginosa)

# Piiraita.
pie(table(Suuralue))
pie(table(Kunto))
pie(table(Hissi))
pie(table(Sauna))
pie(table(Parveke))

# Kuvaajia.
plot(factor(Suuralue), Hinta)
plot(factor(Suuralue), Neli?hinta)
plot(factor(Suuralue), Neli?t)
plot(Rakennusvuosi, Neli?hinta)
abline(lm(Neli?hinta ~ Rakennusvuosi), col = "blue", lwd = 2)



### MONTE CARLO ###
X <- cbind(Rakennusvuosi, Neli?hinta); X <- as.data.frame(X)
plot(X)
abline(lm(Neli?hinta~Rakennusvuosi))
 
d <- dist(X); d <- as.matrix(d); d <- as.data.frame(d); diag(d) <- 100000
dv<-sapply(d,min); dmean<-mean(dv)
dvv<-numeric(1000)
for (i in 1:1000) {dm<-data.frame(x=runif(564, 1906, 2017),
y=runif(564, 923, 6333))
dm<-dist(dm); dm<-as.matrix(dm); dm<-as.data.frame(dm);
diag(dm)<-100000
dvv[i]<-mean(sapply(dm,min))}
hist(dvv, main="Distribution of the mean"); dmean ## 10.67254
## Koska simuloitu mean eroaa paljon todellisesta, voidaan olettaa, ett?
## hinnat eiv?t ole sattumanvaraisesti jakautuneet eri rakennusvuosina.


### RANDOMIZATION TEST ### 
# Nollahypoteesi: keskiarvojen erotus ei ole tilastollisesti merkitsev?.
# Vaihtoehtoinen hypoteesi: keskiarvojen erotus on tilastollisesti merkitsev?.
Y<-cbind(factor(Parveke), Neli?hinta)
y1 <- which(Y[,1] == 1)
y2 <- which(Y[,1] == 2)
Y1 <- Y[y1,]; parveke.kyll? <- Y1[,2]
Y2 <- Y[y2,]; parveke.ei <- Y2[,2]
parveke.kaikki <- Y[,2]
 
d <- mean(parveke.kyll?)-mean(parveke.ei)

# Matriisi permutaatioille.
m<-matrix(nr=178, nc=10000)

# 10000 random permutaatiota.
for (i in 1:10000){m[,i]<-sample(parveke.kaikki, 178)}

data1 <- as.data.frame(m[1:89,])
data2 <- as.data.frame(m[90:178,])

# Molempien keskiarvot.
means1 <- sapply(data1, mean)
means2 <- sapply(data2, mean)
p<-(sum((means1-means2)< -262.4911)+sum((means1-means2)>262.4911))/10000
p ## 0.0528, eli nollahypoteesia ei voida hyl?t?.

plot(density(means1-means2))

plot(factor(Parveke), Neli?hinta)
abline(lm(Neli?hinta~Parveke))

## BOOTSTRAP ## 



## BOOTSTRAP ##

bootstrap.estimate <- function(x) {
   b<-numeric(10)
   for (i in 1:10){b[i]<-mean(sample(x,8,replace=TRUE),
   trim=0.25)}
   plot(density(b))
   mean.est <- sum(b)/10
   return(mean.est)
}
bmean.Neli?t <- bootstrap.estimate(Neli?t) ##  52.305
# Bias-corrected estimaatti
mean(Neli?t)-(mean(Neli?t, trim=0.25)-bmean.Neli?t) ## 52.94351

bmean.Hinta <- bootstrap.estimate(Hinta) ## 141899
# Bias-corrected estimaatti
mean(Hinta)-(mean(Hinta, trim=0.25)-bmean.Hinta) ## 123287.8

bmean.Neli?hinta <- bootstrap.estimate(Neli?hinta) ## 2769.375
# Bias-corrected estimaatti
mean(Neli?hinta) - (mean(Neli?hinta, trim=0.25)-bmean.Neli?hinta) ## 2509.238


library(boot)
theta<-function(x,i){mean(x[i], trim=0.25)}
l <- boot(Neli?t, theta, R=1000)

boot.ci(l)