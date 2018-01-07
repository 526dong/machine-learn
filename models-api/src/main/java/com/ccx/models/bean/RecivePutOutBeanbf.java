package com.ccx.models.bean;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/11/24
 */
public class RecivePutOutBeanbf {

    //请求ID
    private String reqId;

    //请求时间戳
    private String reqTime;

    //返回方式
    private Integer type;

    private DataDescription dataDescription;

    private VariableAnalysis variableAnalysis;

    private ModelOutput modelOutput;

    private OtherOutput otherOutput;


    public static class DataDescription{
        //数据概览
        private Map<String,Object> datasetInfo;

        //离散型变量统计指标
        private List<CateVar> cateVar;

        //连续性变量统计指标
        private List<NumVar> numVar;

        //详细变量分析html文件存放路径
        private String detailVarPath;

        public Map<String, Object> getDatasetInfo() {
            return datasetInfo;
        }

        public void setDatasetInfo(Map<String, Object> datasetInfo) {
            this.datasetInfo = datasetInfo;
        }

        public List<CateVar> getCateVar() {
            return cateVar;
        }

        public void setCateVar(List<CateVar> cateVar) {
            this.cateVar = cateVar;
        }

        public List<NumVar> getNumVar() {
            return numVar;
        }

        public void setNumVar(List<NumVar> numVar) {
            this.numVar = numVar;
        }

        public String getDetailVarPath() {
            return detailVarPath;
        }

        public void setDetailVarPath(String detailVarPath) {
            this.detailVarPath = detailVarPath;
        }
    }
    //离散型变量统计指标
    public static class CateVar{
        private Double  IV;
        private String  Type;
        private Integer  missingN;
        private Double  missing_pct;
        private Integer  nunique;
        private Integer  top1;
        private Double  top1_pct;
        private Integer  top2;
        private Double  top2_pct;
        private Integer  top3;
        private Double  top3_pct;
        private String  vList;
        private String  varName;

        public Double getIV() {
            return IV;
        }

        public void setIV(Double IV) {
            this.IV = IV;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public Integer getMissingN() {
            return missingN;
        }

        public void setMissingN(Integer missingN) {
            this.missingN = missingN;
        }

        public Double getMissing_pct() {
            return missing_pct;
        }

        public void setMissing_pct(Double missing_pct) {
            this.missing_pct = missing_pct;
        }

        public Integer getNunique() {
            return nunique;
        }

        public void setNunique(Integer nunique) {
            this.nunique = nunique;
        }

        public Integer getTop1() {
            return top1;
        }

        public void setTop1(Integer top1) {
            this.top1 = top1;
        }

        public Double getTop1_pct() {
            return top1_pct;
        }

        public void setTop1_pct(Double top1_pct) {
            this.top1_pct = top1_pct;
        }

        public Integer getTop2() {
            return top2;
        }

        public void setTop2(Integer top2) {
            this.top2 = top2;
        }

        public Double getTop2_pct() {
            return top2_pct;
        }

        public void setTop2_pct(Double top2_pct) {
            this.top2_pct = top2_pct;
        }

        public Integer getTop3() {
            return top3;
        }

        public void setTop3(Integer top3) {
            this.top3 = top3;
        }

        public Double getTop3_pct() {
            return top3_pct;
        }

        public void setTop3_pct(Double top3_pct) {
            this.top3_pct = top3_pct;
        }

        public String getvList() {
            return vList;
        }

        public void setvList(String vList) {
            this.vList = vList;
        }

        public String getVarName() {
            return varName;
        }

        public void setVarName(String varName) {
            this.varName = varName;
        }
    }
    //连续性变量统计指标
    public static class NumVar{
        private Double  IV;
        private String  Type;
        private Double  max;
        private Double  mean;
        private Double  median;
        private Double  min;
        private Integer  missingN;
        private Double  missing_pct;
        private Double  quantile1;
        private Double  quantile3;
        private String  range;
        private Double  std;
        private String  varName;

        public Double getIV() {
            return IV;
        }

        public void setIV(Double IV) {
            this.IV = IV;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public Double getMax() {
            return max;
        }

        public void setMax(Double max) {
            this.max = max;
        }

        public Double getMean() {
            return mean;
        }

        public void setMean(Double mean) {
            this.mean = mean;
        }

        public Double getMedian() {
            return median;
        }

        public void setMedian(Double median) {
            this.median = median;
        }

        public Double getMin() {
            return min;
        }

        public void setMin(Double min) {
            this.min = min;
        }

        public Integer getMissingN() {
            return missingN;
        }

        public void setMissingN(Integer missingN) {
            this.missingN = missingN;
        }

        public Double getMissing_pct() {
            return missing_pct;
        }

        public void setMissing_pct(Double missing_pct) {
            this.missing_pct = missing_pct;
        }

        public Double getQuantile1() {
            return quantile1;
        }

        public void setQuantile1(Double quantile1) {
            this.quantile1 = quantile1;
        }

        public Double getQuantile3() {
            return quantile3;
        }

        public void setQuantile3(Double quantile3) {
            this.quantile3 = quantile3;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public Double getStd() {
            return std;
        }

        public void setStd(Double std) {
            this.std = std;
        }

        public String getVarName() {
            return varName;
        }

        public void setVarName(String varName) {
            this.varName = varName;
        }
    }

    //
    public static class  VariableAnalysis{
        //重要变量分析
        private List<ImpVar> impVar;

        //topN变量与目标变量关系表格路径
        private String topNpath;

        public List<ImpVar> getImpVar() {
            return impVar;
        }

        public void setImpVar(List<ImpVar> impVar) {
            this.impVar = impVar;
        }

        public String getTopNpath() {
            return topNpath;
        }

        public void setTopNpath(String topNpath) {
            this.topNpath = topNpath;
        }
    }
    //重要变量分析
    public static class  ImpVar{
        private String Feature_Name;

        private Double gain;

        private Double pct_importance;

        public String getFeature_Name() {
            return Feature_Name;
        }

        public void setFeature_Name(String feature_Name) {
            Feature_Name = feature_Name;
        }

        public Double getGain() {
            return gain;
        }

        public void setGain(Double gain) {
            this.gain = gain;
        }

        public Double getPct_importance() {
            return pct_importance;
        }

        public void setPct_importance(Double pct_importance) {
            this.pct_importance = pct_importance;
        }
    }

    //模型输出
    public static class ModelOutput{
        //训练集测试集数据预览
        private List<Map<String,Object>> modeldataInfo;

        //模型评价指标报告
        private List<Modelreport> modelreport;

        //输出图路径
        private AucksPlot aucksPlot;

        // 概率分析报告
        private List<PvalueReport> pvalueReport;

        public List<Map<String, Object>> getModeldataInfo() {
            return modeldataInfo;
        }

        public void setModeldataInfo(List<Map<String, Object>> modeldataInfo) {
            this.modeldataInfo = modeldataInfo;
        }

        public List<Modelreport> getModelreport() {
            return modelreport;
        }

        public void setModelreport(List<Modelreport> modelreport) {
            this.modelreport = modelreport;
        }

        public AucksPlot getAucksPlot() {
            return aucksPlot;
        }

        public void setAucksPlot(AucksPlot aucksPlot) {
            this.aucksPlot = aucksPlot;
        }

        public List<PvalueReport> getPvalueReport() {
            return pvalueReport;
        }

        public void setPvalueReport(List<PvalueReport> pvalueReport) {
            this.pvalueReport = pvalueReport;
        }
    }

   //模型评价指标报告
    public static class Modelreport{
        private Double AUC;
        private Double KS;
        private Double f1_score;
        private Double gini;
        private Double precision;
        private Double recall;
        private Double support;

       public Double getAUC() {
           return AUC;
       }

       public void setAUC(Double AUC) {
           this.AUC = AUC;
       }

       public Double getKS() {
           return KS;
       }

       public void setKS(Double KS) {
           this.KS = KS;
       }

       public Double getF1_score() {
           return f1_score;
       }

       public void setF1_score(Double f1_score) {
           this.f1_score = f1_score;
       }

       public Double getGini() {
           return gini;
       }

       public void setGini(Double gini) {
           this.gini = gini;
       }

       public Double getPrecision() {
           return precision;
       }

       public void setPrecision(Double precision) {
           this.precision = precision;
       }

       public Double getRecall() {
           return recall;
       }

       public void setRecall(Double recall) {
           this.recall = recall;
       }

       public Double getSupport() {
           return support;
       }

       public void setSupport(Double support) {
           this.support = support;
       }
   }
   //训练集相关图的输出路径
   public static class AucksPlot{
        //训练集KS指标路径
        private String trainKSpath;
        //训练集AUC指标路径
        private String trainAUCpath;
        //测试集KS指标路径
        private String testKSpath;
        //训练集AUC指标路径
        private String testAUCpath;

       public String getTrainKSpath() {
           return trainKSpath;
       }

       public void setTrainKSpath(String trainKSpath) {
           this.trainKSpath = trainKSpath;
       }

       public String getTrainAUCpath() {
           return trainAUCpath;
       }

       public void setTrainAUCpath(String trainAUCpath) {
           this.trainAUCpath = trainAUCpath;
       }

       public String getTestKSpath() {
           return testKSpath;
       }

       public void setTestKSpath(String testKSpath) {
           this.testKSpath = testKSpath;
       }

       public String getTestAUCpath() {
           return testAUCpath;
       }

       public void setTestAUCpath(String testAUCpath) {
           this.testAUCpath = testAUCpath;
       }
   }

   //概率分析报告
   public static class PvalueReport{
        private Double IV;
        private Integer bad;
        private Double bad_per;
        private Double bins_score;
        private Double factor_per;
        private Integer good;
        private Double model_pvalue;
        private Integer total;

       public Double getIV() {
           return IV;
       }

       public void setIV(Double IV) {
           this.IV = IV;
       }

       public Integer getBad() {
           return bad;
       }

       public void setBad(Integer bad) {
           this.bad = bad;
       }

       public Double getBad_per() {
           return bad_per;
       }

       public void setBad_per(Double bad_per) {
           this.bad_per = bad_per;
       }

       public Double getBins_score() {
           return bins_score;
       }

       public void setBins_score(Double bins_score) {
           this.bins_score = bins_score;
       }

       public Double getFactor_per() {
           return factor_per;
       }

       public void setFactor_per(Double factor_per) {
           this.factor_per = factor_per;
       }

       public Integer getGood() {
           return good;
       }

       public void setGood(Integer good) {
           this.good = good;
       }

       public Double getModel_pvalue() {
           return model_pvalue;
       }

       public void setModel_pvalue(Double model_pvalue) {
           this.model_pvalue = model_pvalue;
       }

       public Integer getTotal() {
           return total;
       }

       public void setTotal(Integer total) {
           this.total = total;
       }
   }

   public static class  OtherOutput{
        //所有数据预测概率文件路径
        private String predictResPath;
        //分析过程的日志文件路径
        private String logPath;
        //分析结果的Excel表格下载路径
        private String analysisReport;

       public String getPredictResPath() {
           return predictResPath;
       }

       public void setPredictResPath(String predictResPath) {
           this.predictResPath = predictResPath;
       }

       public String getLogPath() {
           return logPath;
       }

       public void setLogPath(String logPath) {
           this.logPath = logPath;
       }

       public String getAnalysisReport() {
           return analysisReport;
       }

       public void setAnalysisReport(String analysisReport) {
           this.analysisReport = analysisReport;
       }
   }


    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public DataDescription getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(DataDescription dataDescription) {
        this.dataDescription = dataDescription;
    }

    public VariableAnalysis getVariableAnalysis() {
        return variableAnalysis;
    }

    public void setVariableAnalysis(VariableAnalysis variableAnalysis) {
        this.variableAnalysis = variableAnalysis;
    }

    public ModelOutput getModelOutput() {
        return modelOutput;
    }

    public void setModelOutput(ModelOutput modelOutput) {
        this.modelOutput = modelOutput;
    }

    public OtherOutput getOtherOutput() {
        return otherOutput;
    }

    public void setOtherOutput(OtherOutput otherOutput) {
        this.otherOutput = otherOutput;
    }
}
