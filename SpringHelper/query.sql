CREATE TABLE movie_ranks (
    id                 int          not null primary key auto_increment,
    target_dt          date         not null,
    `rank`             int          not null,
    rank_inten         int          not null,
    rank_old_and_new   varchar(10)  not null,
    movie_cd           varchar(20)  not null,
    movie_nm           varchar(255) not null,
    open_dt            date         not null,
    sales_amt          bigint       not null,
    sales_share        float        not null,
    sales_inten        bigint       not null,
    sales_change       float        not null,
    sales_acc          bigint       not null,
    audi_cnt           int          not null,
    audi_inten         bigint       not null,
    audi_change        float        not null,
    audi_acc           int          not null,
    scrn_cnt           int          not null,
    show_cnt           int          not null,
    reg_date           datetime     not null,
    edit_date          datetime     not null
) ENGINE=INNODB DEFAULT CHARSET UTF8;

