package com.wj.testhttps;

import com.wj.rxhttp.base.BaseResponse;

import java.util.List;

/**
 * Created by wenjin on 2017/7/3.
 */

public class BookBean extends BaseResponse{
    private String success;
    private BookResult result;
    class BookResult{
        private String BookShelfName;
        private String Version;
        private String ProjectName;
        private List<Book> Book;
        class Book{
            private String Id;
            private String pid;
            private String bookName;
            private String bookType;
            private String logo;
            private String coverName;
            private String fileName;
            private String updataDate;
            private String zipName;
            private String url;
            private String keyWord;
            private String firstProgramName;
            private String secondProgramName;
            private String createTime;
            private String createBy;
            private String modifyTime;
            private String modifyby;
            private String firstname;
            private String parentid;
            private String secondname;
            private String productName;
            private String sorts;
            private String isCollection;

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getBookName() {
                return bookName;
            }

            public void setBookName(String bookName) {
                this.bookName = bookName;
            }

            public String getBookType() {
                return bookType;
            }

            public void setBookType(String bookType) {
                this.bookType = bookType;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCoverName() {
                return coverName;
            }

            public void setCoverName(String coverName) {
                this.coverName = coverName;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getUpdataDate() {
                return updataDate;
            }

            public void setUpdataDate(String updataDate) {
                this.updataDate = updataDate;
            }

            public String getZipName() {
                return zipName;
            }

            public void setZipName(String zipName) {
                this.zipName = zipName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getKeyWord() {
                return keyWord;
            }

            public void setKeyWord(String keyWord) {
                this.keyWord = keyWord;
            }

            public String getFirstProgramName() {
                return firstProgramName;
            }

            public void setFirstProgramName(String firstProgramName) {
                this.firstProgramName = firstProgramName;
            }

            public String getSecondProgramName() {
                return secondProgramName;
            }

            public void setSecondProgramName(String secondProgramName) {
                this.secondProgramName = secondProgramName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public String getModifyby() {
                return modifyby;
            }

            public void setModifyby(String modifyby) {
                this.modifyby = modifyby;
            }

            public String getFirstname() {
                return firstname;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getSecondname() {
                return secondname;
            }

            public void setSecondname(String secondname) {
                this.secondname = secondname;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getSorts() {
                return sorts;
            }

            public void setSorts(String sorts) {
                this.sorts = sorts;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(String isCollection) {
                this.isCollection = isCollection;
            }

            @Override
            public String toString() {
                return "Book{" +
                        "Id='" + Id + '\'' +
                        ", pid='" + pid + '\'' +
                        ", bookName='" + bookName + '\'' +
                        ", bookType='" + bookType + '\'' +
                        ", logo='" + logo + '\'' +
                        ", coverName='" + coverName + '\'' +
                        ", fileName='" + fileName + '\'' +
                        ", updataDate='" + updataDate + '\'' +
                        ", zipName='" + zipName + '\'' +
                        ", url='" + url + '\'' +
                        ", keyWord='" + keyWord + '\'' +
                        ", firstProgramName='" + firstProgramName + '\'' +
                        ", secondProgramName='" + secondProgramName + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", createBy='" + createBy + '\'' +
                        ", modifyTime='" + modifyTime + '\'' +
                        ", modifyby='" + modifyby + '\'' +
                        ", firstname='" + firstname + '\'' +
                        ", parentid='" + parentid + '\'' +
                        ", secondname='" + secondname + '\'' +
                        ", productName='" + productName + '\'' +
                        ", sorts='" + sorts + '\'' +
                        ", isCollection='" + isCollection + '\'' +
                        '}';
            }
        }

        public String getBookShelfName() {
            return BookShelfName;
        }

        public void setBookShelfName(String bookShelfName) {
            BookShelfName = bookShelfName;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String version) {
            Version = version;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }

        public List<BookResult.Book> getBook() {
            return Book;
        }

        public void setBook(List<BookResult.Book> book) {
            Book = book;
        }

        @Override
        public String toString() {
            return "BookResult{" +
                    "BookShelfName='" + BookShelfName + '\'' +
                    ", Version='" + Version + '\'' +
                    ", ProjectName='" + ProjectName + '\'' +
                    ", Book=" + Book +
                    '}';
        }
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public BookResult getResult() {
        return result;
    }

    public void setResult(BookResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
