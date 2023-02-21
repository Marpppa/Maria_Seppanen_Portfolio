# -*- coding: utf-8 -*-

## Matemaattiset ja tilastolliset ohjelmistot, kevät 2021
## Harjoitustyö
## Maria Seppänen, maria.seppanen@tuni.fi
## 30.4.2021


import pandas;
import matplotlib.pyplot;
import numpy;
import scipy;
import scipy.stats;
import sklearn;
import sklearn.linear_model;
import sklearn.metrics;

# Heataan data tiedostosta dataframeen.
data = pandas.read_csv('C:/Users/35845/Desktop/Matemaattiset ohjelmistot/Python/harjoitustyodata/harjoitustyodata.csv',sep=',',header='infer',quotechar='\"');


def get_group(word_data, group):
    output = word_data[word_data["groupID"] == group];
    return((output));
    
def print_osa1(group, name):
    
    # lukumäärä
    print(f"{name}:")
    print(f"lukumäärä: {sum(group)}")
    
    # tunnusluvut
    print(f"{max(group)}")
    print(f"keskiarvo: {group.mean()}, mediaani: {group.median()}, keskihajonta: {group.std()}")
    print(f"0,01% kvartiili: {group.quantile(.001)}, 0,99% kvartiili: {group.quantile(.999)}")
    print()
   
def histogrammi(data):
    # Tallennetaan ryhmät ja niiden arvojen lukumäärät.
    groups = list(data.keys())
    values = list(data.values())
    
    # Asetetaan sopiva koko.
    matplotlib.pyplot.figure(figsize = (10, 5))
    
    # Piirretään histogrammi.
    matplotlib.pyplot.bar(groups, values, color ='blue', width = 0.4);
    matplotlib.pyplot.show()
     
def ttest(jakauma1, jakauma2):
        teststatistic,pvalue=scipy.stats.ttest_ind(jakauma1, jakauma2, axis=0)    
        return(pvalue)


def osa1(data):
    
    # Haetaan sanojen lukumäärät ja ryhmätunnukset.
    freedom = data[["freedom", "groupID"]]
    nation = data[["nation", "groupID"]]
    logic = data[["logic", "groupID"]]
    normal = data[["normal", "groupID"]]
    program = data[["program", "groupID"]]
    
    
    
    # Haetaan jokaisen eri ryhmän omat lukumäärät.
    
    ## FREEDOM
    freedom_sci_crypt = get_group(freedom, 12)["freedom"]
    freedom_talk_politics_guns = get_group(freedom, 17)["freedom"]
    freedom_talk_politics_mideast = get_group(freedom, 18)["freedom"]
    freedom_talk_politics_misc = get_group(freedom, 19)["freedom"]
    
    print("sci_crypt")
    print_osa1(freedom_sci_crypt, "freedom")
    print("talk.politics.guns")
    print_osa1(freedom_talk_politics_guns, "freedom")
    print("talk.politics.mideast")
    print_osa1(freedom_talk_politics_mideast, "freedom")
    print("talk.politics.misc")
    print_osa1(freedom_talk_politics_misc, "freedom")
    
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    y=numpy.array([1, 2, 3, 4]);
    pylvasnimet=['sci.crypt','talk.politics.guns','talk.politics.mideast','talk.politics.misc'];
    a=[sum(freedom_sci_crypt), sum(freedom_talk_politics_guns), sum(freedom_talk_politics_mideast), sum(freedom_talk_politics_misc)];
    
    myaxes.barh(y,width=a,height=0.2,label='freedom');
    myaxes.set_yticks(y);
    myaxes.set_yticklabels(pylvasnimet);
    myaxes.legend();
    
    
    ## NATION
    nation_talk_politics_guns = get_group(nation, 17)["nation"]
    nation_talk_politics_mideast = get_group(nation, 18)["nation"]
    nation_talk_politics_misc = get_group(nation, 19)["nation"]
    
    print("talk.politics.guns")
    print_osa1(nation_talk_politics_guns, "nation")
    print("talk.politics.mideast")
    print_osa1(nation_talk_politics_mideast, "nation")
    print("talk.politics.misc")
    print_osa1(nation_talk_politics_misc, "nation")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    y=numpy.array([1, 2, 3]);
    pylvasnimet=['talk.politics.guns','talk.politics.mideast','talk.politics.misc'];
    a=[sum(nation_talk_politics_guns), sum(nation_talk_politics_mideast), sum(nation_talk_politics_misc)];
    
    myaxes.barh(y,width=a,height=0.2,label='nation');
    myaxes.set_yticks(y);
    myaxes.set_yticklabels(pylvasnimet);
    myaxes.legend();
    
    ## LOGIC
    logic_alt_atheism = get_group(logic, 1)["logic"]
    logic_sci_electronics = get_group(logic, 13)["logic"]
    logic_talk_politics_misc = get_group(logic, 19)["logic"]
    logic_talk_religion_misc = get_group(logic, 20)["logic"]
    
    print("logic.alt.atheism")
    print_osa1(logic_alt_atheism, "logic")
    print("logic.sci.electronics")
    print_osa1(logic_sci_electronics, "logic")
    print("talk.politics.misc")
    print_osa1(logic_talk_politics_misc, "logic")
    print("logic.talk.religion")
    print_osa1(logic_talk_religion_misc, "logic")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    y=numpy.array([1, 2, 3, 4]);
    pylvasnimet=['logic.alt.atheism','logic.sci.electronics','talk.politics.misc', 'talk.religion.misc'];
    a=[sum(logic_alt_atheism), sum(logic_sci_electronics), sum(logic_talk_politics_misc), sum(logic_talk_religion_misc)];
    
    myaxes.barh(y,width=a,height=0.2,label='logic');
    myaxes.set_yticks(y);
    myaxes.set_yticklabels(pylvasnimet);
    myaxes.legend();
    
    ## NORMAL
    normal_comp_graphics = get_group(normal, 2)["normal"]
    normal_comp_windows_x = get_group(normal, 6)["normal"]
    normal_sci_electronics = get_group(normal, 13)["normal"]
    normal_sci_med = get_group(normal, 14)["normal"]
    
    print("comp.graphics")
    print_osa1(normal_comp_graphics, "normal")
    print("comp.windows.x")
    print_osa1(normal_comp_windows_x, "normal")
    print("sci.electronics")
    print_osa1(normal_sci_electronics, "normal")
    print("sci.med")
    print_osa1(normal_sci_med, "normal")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    y=numpy.array([1, 2, 3, 4]);
    pylvasnimet=['comp.graphics','comp.windows.x','sci.electronics', 'sci.med'];
    a=[sum(normal_comp_graphics), sum(normal_comp_windows_x), sum(normal_sci_electronics), sum(normal_sci_med)];
    
    myaxes.barh(y,width=a,height=0.2,label='normal');
    myaxes.set_yticks(y);
    myaxes.set_yticklabels(pylvasnimet);
    myaxes.legend();
    
    ## PROGRAM
    program_comp_graphics = get_group(program, 2)["program"]
    program_comp_windows_x = get_group(program, 6)["program"]
    program_talk_politics_misc = get_group(program, 19)["program"]
    program_comp_sys_hardware = get_group(program, 5)["program"]
    
    
    print("comp.graphics")
    print_osa1(program_comp_graphics, "program")
    print("comp.windows.x")
    print_osa1(program_comp_windows_x, "program")
    print("talk.politics.misc")
    print_osa1(program_talk_politics_misc, "program")
    print("comp.sys.hardware")
    print_osa1(program_comp_sys_hardware, "program")
    
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    y=numpy.array([1, 2, 3, 4]);
    pylvasnimet=['comp.graphics','comp.windows.x','talk.politics.misc', 'comp.sys.hardware'];
    a=[sum(program_comp_graphics), sum(program_comp_windows_x), sum(program_talk_politics_misc), sum(program_comp_sys_hardware)];
    
    myaxes.barh(y,width=a,height=0.2,label='program');
    myaxes.set_yticks(y);
    myaxes.set_yticklabels(pylvasnimet);
    myaxes.legend();

    
def osa2(data):
    groupIDs = data[["groupID"]]
    words = data.iloc[:,5:967]
    
    word_data = pandas.concat([groupIDs, words], axis=1, join='inner')
    
    # Viestien pituudet
    word_data["sum"] = word_data[words.columns].sum(axis=1)
    
    rec_sport_baseball =get_group(word_data, 10)["sum"]
    rec_sport_hockey = get_group(word_data, 11)["sum"]


    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(rec_sport_baseball, bins="auto", histtype="bar")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(rec_sport_hockey, bins="auto", histtype="bar")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(numpy.log(rec_sport_baseball), bins="auto", histtype="bar")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(numpy.log(rec_sport_hockey), bins="auto", histtype="bar")
    
    teststatistic,pvalue=scipy.stats.ttest_ind(numpy.log(rec_sport_baseball),numpy.log(rec_sport_hockey),axis=0)
    print(pvalue) # 0.24743033891447788 -> nollahypoteesi jää voimaan
    
    rec_autos = numpy.log(get_group(word_data, 8)["sum"])
    rec_motorcycles = numpy.log(get_group(word_data, 9)["sum"])
    
    teststatistic,pvalue=scipy.stats.ttest_ind(rec_autos,rec_motorcycles,axis=0)
    print(pvalue) # 6.070535775703711e-05 -> nollahypoteesi hylätään
      

def osa3(data):
    time = data["secsfrommidnight"]/3600
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(time, bins="auto", histtype="bar")
    
    time_8am = data["secsfrom8am"]/3600
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(time_8am, bins="auto", histtype="bar") # näyttää enemmän normaalijakautuneelta
    
    print(f"Kirjoitusajan keskiarvo: {time_8am.mean()}, mediaani: {time_8am.median()} ja keskihajonta: {time_8am.std()}.")
    
    time_comp_graphics = get_group(data, 2)["secsfrom8am"]/3600
    time_soc_religion_christian = get_group(data, 16)["secsfrom8am"]/3600
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(time_comp_graphics, bins="auto", histtype="bar")
    print(f"Comp.graphics keskiarvo: {time_comp_graphics.mean()}")
    
    myfigure, myaxes = matplotlib.pyplot.subplots();
    myaxes.hist(time_soc_religion_christian, bins="auto", histtype="bar")
    print(f"Soc.religion.christian keskiarvo: {time_soc_religion_christian.mean()}")
    
    teststatistic,pvalue=scipy.stats.ttest_ind(time_comp_graphics, time_soc_religion_christian, axis=0)
    print(pvalue) # 1.5025984582022796e-33 nollahypoteesi hylätään -> keskiarvoissa on merkittävä ero
    

def osa4(data):
    jpeg = data["jpeg"]
    gif = data["gif"]
    
    print(f"Jpeg ja gif korrelaatio: {jpeg.corr(gif)}.")
    
    write = data["write"]
    sale = data["sale"]
    
    print(f"Write ja sale korrelaatio: {write.corr(sale)}.")
    
    jpeg_comp_graphics = get_group(data, 2)["jpeg"]
    gif_comp_graphics = get_group(data, 2)["gif"]
    
    print(f"Jpeg ja gif korrelaatio ryhmässä comp.graphics: {jpeg_comp_graphics.corr(gif_comp_graphics)}.") 
     
    
def osa5(data):
    sentiment = data["meanvalences"]
    teststatistic,pvalue2=scipy.stats.normaltest(sentiment)
    print(pvalue2)
    # p-arvo on hyvin pieni, eli nollahypoteesi hylätään ja sentimentti ei ole normaalijakautunut
    
    
    sentiment_median = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    sentiment_mean = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    sentiment_std = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    sentiment_1st = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    sentiment_3rd = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    sentiment_sum = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    hist_data = {0 : 0}
    
    
    
    for i in range(1,21):
        sentiment_med = get_group(data, i)["meanvalences"]
        sentiment_median[i] = sentiment_med.median()
        sentiment_mean[i] = sentiment_med.mean()
        sentiment_std[i] = sentiment_med.std()
        sentiment_1st[i] = sentiment_med.quantile(0.25)
        sentiment_3rd[i] = sentiment_med.quantile(0.75)
        sentiment_sum[i] = sentiment_med.sum()
        hist_data[i] = sentiment_sum[i]
        
    for i in range(1,21):
        print(f"Ryhmän {i} keskiarvo: {sentiment_mean[i]:.4f}, mediaani: {sentiment_median[i]:.4f}, keskihajonta: {sentiment_std[i]:.4f}")
        print(f"25% kvartiili: {sentiment_1st[i]:.4f} ja 75% kvartiili {sentiment_3rd[i]:.4f}.")
        print()
    

    histogrammi(hist_data) 
    # positiivisimmat ryhmät on 2 (comp.graphics), 7 (misc.forsale) ja 13 (sci.electronics).
    # negatiiivisimmat ryhmät ovat 17 (talk.politics.guns), 18 (talk.politics.mideast ) ja 19 (talk.politics.misc).
    
    
    sentiment_comp_sys_ibm = get_group(data, 4)["meanvalences"]
    sentiment_comp_sys_mac = get_group(data, 5)["meanvalences"]
    pvalue = ttest(sentiment_comp_sys_ibm, sentiment_comp_sys_mac)
    print(pvalue) # 0.0014340022828717297 -> nollahypoteesi hylätään
    
    sentiment_rec_sport_baseball = get_group(data, 10)["meanvalences"]
    sentiment_rec_sport_hockey = get_group(data, 11)["meanvalences"]
    pvalue = ttest(sentiment_rec_sport_baseball, sentiment_rec_sport_hockey)
    print(pvalue) # 0.8983835913671558 -> nollahypoteesi jää voimaan
    
    sentiment_rec_autos = get_group(data, 8)["meanvalences"]
    sentiment_rec_motorcycles = get_group(data, 9)["meanvalences"]
    pvalue = ttest(sentiment_rec_autos, sentiment_rec_motorcycles)
    print(pvalue) # 0.00273396989329422 -> nollahypoteesi hylätään
    

def osa6(data):

    words1= get_group(data, 2)
    words1["tavoitearvo"] = [1]*len(words1)

    words2 = get_group(data, 15)
    words2["tavoitearvo"] = [-1]*len(words2)
    
    word_data = pandas.concat([words1, words2])
    
    ### a 
    # Syötemuuttuja
    X = word_data[["jpeg"]]
    
    y = pandas.concat([words1["tavoitearvo"], words2["tavoitearvo"]], axis=0)

    y=y.to_numpy()
    X=X.to_numpy()

    # Tehdään lineaariregressio
    tulos=sklearn.linear_model.LinearRegression().fit(X,y);


    ypred=tulos.predict(X);

    # Piirretään todelliset vs. ennustetut arvot
    myfigure,myaxes=matplotlib.pyplot.subplots()
    myaxes.scatter(y,ypred,s=20,c='g')
    
    print(f"Keskimääräinen neliövirhe: {sklearn.metrics.mean_squared_error(y, ypred)}.")
    print()
    ### b
    # Syötemuuttuja
    X = word_data[["jpeg", "earth"]]

    X=X.to_numpy()

    # Tehdään lineaariregressio
    tulos=sklearn.linear_model.LinearRegression().fit(X,y);

    ypred=tulos.predict(X);

    # Piirretään todelliset vs. ennustetut arvot
    myfigure,myaxes=matplotlib.pyplot.subplots()
    myaxes.scatter(y,ypred,s=20,c='g')
    
    
    print(f"Keskimääräinen neliövirhe: {sklearn.metrics.mean_squared_error(y, ypred)}.")
    print()
    
    
    ### c
    groups1 = get_group(data, 2)
    groups2 = get_group(data, 15)
    
    groups = pandas.concat([groups1, groups2], axis=0)
    
    X = groups[["group1", "group2", "group3", "group4", "group5", "group6", "group7", "group8"]]
    
    X=X.to_numpy()
    

    # Tehdään lineaariregressio
    tulos=sklearn.linear_model.LinearRegression().fit(X,y);

    ypred=tulos.predict(X);

    # Piirretään todelliset vs. ennustetut arvot
    myfigure,myaxes=matplotlib.pyplot.subplots()
    myaxes.scatter(y,ypred,s=20,c='g')
        
        
    print(f"Painokertoimet ovat: {tulos.coef_}.")
    print(f"Keskimääräinen neliövirhe: {sklearn.metrics.mean_squared_error(y, ypred)}.")
    # regressiomalli on parempi kuin kaksi edellistä, paras muuttuja on ryhmä 6 ja toinen ryhmä 5
    print()
   
    
    
#osa1(data)
#osa2(data)
#osa3(data)
#osa4(data)
#osa5(data)
osa6(data)







